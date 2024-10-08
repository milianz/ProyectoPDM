import Publication from "../models/publication.model.js";
import User from "../models/user.model.js";
import { uploadImage } from "../cloudinary.js";
import fs from "fs-extra";

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
    availability,
    scheduleViewing,
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
      propertyParking: Number(propertyParking),
      propertyFurnished,
      propertyDescription,
      propertyPrice,
      availability,
      scheduleViewing: JSON.parse(scheduleViewing),
      propertyImages: [],
      seller: userFound._id,
    });

    if (req.files && req.files.images) {
      const imageFiles = Array.isArray(req.files.images) ? req.files.images : [req.files.images];
      for (const image of imageFiles) {
        const result = await uploadImage(image.tempFilePath);
        newPublication.propertyImages.push({
          public_id: result.public_id,
          secure_url: result.secure_url,
        });
        await fs.unlink(image.tempFilePath);
      }
    }

    const publicationSaved = await newPublication.save();

    res.status(201).json({
      message: "Publication created successfully",
      publication: publicationSaved,
    });
  } catch (error) {
    console.error("Error creating publication:", error);
    res.status(500).json({ error: "Publication creation failed" });
  }
};
//! method to get all approved publications
export const getAllPublication = async (res) => {
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
export const getAllPublicationByUser = async (res) => {};

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
