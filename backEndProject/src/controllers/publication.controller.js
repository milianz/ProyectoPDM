import Publication from "../models/publication.model.js";
import User from "../models/user.model.js";
import {uploadImage} from "../cloudinary.js";
import fs from 'fs/promises'; 
//! method to create a publications
// Importa fs desde las promesas para eliminar archivos

export const createPublication = async (req, res) => {
  const {
    type,
    state,
    city,
    town,
    address,
    area,
    rooms,
    latitude,
    longitude,
    floors,
    description,
    price,
  } = req.body;

  try {
    // Verifica si el usuario existe
    const userFound = await User.findById(req.user.id);
    if (!userFound) {
      return res.status(400).json({ message: "User not found" });
    }

    // Crea una nueva instancia de Publicación
    let newPublication = new Publication({
      type,
      state,
      city,
      town,
      address,
      area,
      rooms,
      latitude,
      longitude,
      floors,
      description,
      price,
      seller: userFound._id,
    });

    // Si se proporciona una imagen, súbela a Cloudinary y actualiza la publicación
    if (req.files?.image) {
      const result = await uploadImage(req.files.image.tempFilePath);
      console.log(result); // Puedes registrar el resultado de la subida en la consola para verificar

      // Asigna el public_id y secure_url de la imagen subida a la publicación
      newPublication.image = {
        public_id: result.public_id,
        secure_url: result.secure_url,
      };

      // Elimina el archivo temporal después de la subida
      await fs.unlink(req.files.image.tempFilePath);
    }

    // Guarda la nueva publicación en la base de datos
    const publicationSaved = await newPublication.save();

    // Devuelve una respuesta con el estado 201 y los datos de la publicación guardada
    res.status(201).json({
      message: "Publication created successfully",
      publication: publicationSaved,
    });
  } catch (error) {
    // Maneja errores y registra un mensaje de error genérico
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
export const getAllPublicationByUser = async (res) => {
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
