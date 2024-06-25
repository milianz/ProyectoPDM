import Publication from "../models/publication.model.js";
import User from "../models/user.model.js";
import { uploadImage } from "../cloudinary.js";
import fs from "fs-extra";
import fetch from 'node-fetch';
import path from 'path';

//! method to create a publications
export const createPublication = async (req, res) => {
  const {
    propertyType,
    neighborhood,
    municipality,
    department,
    propertyAddress,
    longitude,
    latitude,
    propertySize,
    propertyBedrooms,
    propertyBathrooms,
    propertyFloors,
    propertyParking,
    propertyFurnished,
    propertyDescription,
    propertyPrice,
    scheduleViewing,
    imageUris,
  } = req.body;

  try {
    const userFound = await User.findById(req.user.id);
    if (!userFound) {
      return res.status(400).json({ message: "User not found" });
    }

    
    let newPublication = new Publication({
      propertyType,
      neighborhood,
      municipality,
      department,
      propertyAddress,
      longitude,
      latitude,
      propertySize,
      propertyBedrooms,
      propertyBathrooms,
      propertyFloors,
      propertyParking: Number(propertyParking), // Asegúrate de que sea un número
      propertyFurnished,
      propertyDescription,
      propertyPrice,
      scheduleViewing: scheduleViewing,
      images: [],
      seller: userFound._id,
    });

    if(req.files && req.files.images){
      const images = Array.isArray(req.files.images)
       ? req.files.images 
       : [req.files.images];
       for (const image of images) {
        const result = await uploadImage(image.tempFilePath);
        newPublication.images.push({
          public_id: result.public_id,
          secure_url: result.secure_url,
        });
       }
       images.forEach((image)=> fs.unlink(image.tempFilePath));
    }


    if (imageUris) {
      const uris = Array.isArray(imageUris) ? imageUris : [imageUris];
      for (const uri of uris) {
        const response = await fetch(uri);
        const buffer = await response.buffer();
        const tempFilePath = path.join('/tmp', `${Date.now()}-${path.basename(uri)}`);
        fs.writeFileSync(tempFilePath, buffer);
        const result = await uploadImage(tempFilePath);
        newPublication.images.push({
          public_id: result.public_id,
          secure_url: result.secure_url,
        });
        fs.unlinkSync(tempFilePath);
      }
    }

    if (req.body.images) {
      const imagePaths = Array.isArray(req.body.images) ? req.body.images : [req.body.images];
      for (const image of imagePaths) {
        try {
          const tempFilePath = path.join('/tmp', `${Date.now()}-${path.basename(image.path)}`);
          fs.copyFileSync(image.path, tempFilePath);
          const result = await uploadImage(tempFilePath);
          newPublication.images.push({
            public_id: result.public_id,
            secure_url: result.secure_url,
          });
          fs.unlinkSync(tempFilePath);
        } catch (error) {
          console.error('Error copying file:', error);
        }
      }
    }

    const publicationSaved = await newPublication.save();

    res.status(201).json({
      message: "Publication created successfully",
    });
  } catch (error) {
    console.error("Error creating publication:", error);
    res.status(500).json({ error: "Publication creation failed" });
  }
};
//! method to get all approved publications
// Adjust the import according to your file structure
export const getAllPublication = async (req, res) => {
  try {
    const publications = await Publication.find({
      status: "approved",
    }).populate("seller", "name lastName");
    res.status(200).json(publications);
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: "Failed to retrieve approved publications" });
  }
};

//TODO method to get all approved publications
export const getAllPublicationByUser = async (req,res) => {
  try {
    const publications = await Publication.find({
      status: "approved",
      seller: req.user.id,
    }).populate("seller", "name lastName");
    res.status(200).json(publications);
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: "Failed to retrieve approved publications" });
  }
};

//TODO method for delete only the publications made by the user
export const deleteUserPublication = async (req, res) => {};

//! method for update status ONLY FOR ADMINS
export const updatePublicationStatus = async (req, res) => {
  try {
    if (!req.user) {
      return res
        .status(401)
        .json({ error: "Unauthorized: Authentication required" });
    }

    const user = await User.findById(req.user.id);
    if (!user || user.role !== "admin") {
      return res.status(403).json({
        error: "Forbidden: Only admin users can update publication status",
      });
    }

    const publication = await Publication.findByIdAndUpdate(
      req.params.id,
      req.body,
      { new: true }
    );

    if (!publication) {
      return res.status(404).json({ error: "Publication not found" });
    }

    res.status(200).json({
      message: "Publication status updated successfully",
      publication,
    });
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: "Failed to update publication status" });
  }
};

//! method for get only pending publications ONLY FOR ADMINS
export const getPendingPublication = async (req, res) => {
  try {
    const user = await User.findById(req.user.id);
    if (!user || user.role !== "admin") {
      return res.status(403).json({
        error: "Forbidden: Only admin users can view pending publications",
      });
    }
    const pendingPublications = await Publication.find({
      status: "pending",
    }).populate("seller", "name lastName");
    res.status(200).json(pendingPublications);
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: "Failed to retrieve pending publications" });
  }
};

//! method for get only unapproved publications ONLY FOR ADMINS
export const getUnapprovedPublication = async (req, res) => {
  try {
    const user = await User.findById(req.user.id);
    if (!user || user.role !== "admin") {
      return res.status(403).json({
        error: "Forbidden: Only admin users can view unapproved publications",
      });
    }

    const unapprovedPublications = await Publication.find({
      status: "unapproved",
    }).populate("seller", "name lastName");
    res.status(200).json(unapprovedPublications);
  } catch (error) {
    console.log(error);
    res
      .status(500)
      .json({ error: "Failed to retrieve unapproved publications" });
  }
};

//TODO method for delete any publications ONLY FOR ADMINS
export const deleteAdminPublication = async (req, res) => {};

export const getAllPublicationById = async (req, res) => {
  try {
    const publication = await Publication.findById(req.params.id).populate("seller", "name lastName");
    res.status(200).json(publication);
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: "Failed to retrieve publication" });
  }
}