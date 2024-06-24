import jwt from 'jsonwebtoken'
import {SECRETE_KEY} from '../config.js'


export const authRequired = (req, res, next) => {
  const token = req.cookies.token || req.headers['authorization'];

  if (!token) {
    return res.status(401).json({ message: "No token used, authorization denied" });
  }

  jwt.verify(token, SECRETE_KEY, (err, user) => {
    if (err) return res.status(404).json({ message: "Invalid token" });
    req.user = user;
    next();
  });
};
