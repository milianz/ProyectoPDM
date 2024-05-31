import Publication from "../models/publication.model.js";
import User from "../models/user.model.js";

export const createPublication = async (req, res) => {
  const {
    type,
    state,
    city,
    town,
    address,
    area,
    rooms,
    floors,
    other,
    description,
    price,
    availability,
    images,
  } = req.body;

  try {
    const userFound = await User.findById(req.user.id);

    if (!userFound) return res.status(400).json({ message: " User not found" });

    const newPublication = new Publication({
      type,
      state,
      city,
      town,
      address,
      area,
      rooms,
      floors,
      other,
      description,
      price,
      availability,
      images,
      seller: userFound._id,
      status: "unapproved",
    });

    const publicationSaved = await newPublication.save();

    res.status(201).json({
      message: "Publication created successfully",
      publication: publicationSaved,
    });
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: "Publication creation failed" });
  }
};
export const getAllPublication = async (req, res) => {
  try {
    const publications = await Publication.find().populate(
      "seller",
      "name lastName"
    );
    res.status(200).json(publications);
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: "Failed to retrieve publications" });
  }
};
export const updatePublication = async (req, res) => {};
export const deletePublication = async (req, res) => {};
