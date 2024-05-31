import User from "../models/user.model.js";
import bcrypt from "bcryptjs";
import { createAccessToken } from "../libs/jwt.js";
import { json } from "express";

export const register = async (req, res) => {
  const { email, password, name, lastName } = req.body;

  try {
    const passwordHash = await bcrypt.hash(password, 10);

    const newUser = new User({
      name,
      lastName,
      email,
      password: passwordHash,
    });

    const userSaved = await newUser.save();

    const token = await createAccessToken({ id: userSaved._id });

    res.cookie("token", token);
    res.json({
      message: "User created successfully",
    });
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: "Registration failed" });
  }
};

export const login = async (req, res) => {
  const { email, password } = req.body;

  try {
    const userFound = await User.findOne({ email });

    if (!userFound)
      return res.status(400).json({ message: "Invalid credential" });
    const isMatch = await bcrypt.compare(password, userFound.password);

    if (!isMatch)
      return res.status(400).json({ message: "Invalid credential" });

    const token = await createAccessToken({ id: userFound._id });

    res.cookie("token", token);
    res.json({
      id: userFound._id,
      userName: userFound.userName,
      email: userFound.email,
      createdAt: userFound.createdAt,
      updatedAt: userFound.createdAt,
    });
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: "Registration failed" });
  }
};

export const logout = (req,res) =>{
    res.cookie('token', '', {
        expires: new Date(0),
        httpOnly: true,
        secure: true,
        sameSite: 'strict'
    });

    return res.status(200).send('Cookie cleared');
};

export const profile = async (req,res) =>{
  const userFound =  await User.findById(req.user.id)
  
  if (!userFound) return res.status(400).json({message: " User not found"});

  return res.json({
    id: userFound._id,
    userName: userFound.userName,
    email: userFound.email,
    createdAt: userFound.createdAt,
    updatedAt: userFound.createdAt,
  })

  res.json('profile')
  
}