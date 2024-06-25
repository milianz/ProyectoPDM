import { Router } from "express";
import { authRequired } from "../middleware/validateToken.js";
import {
  createPublication,
  updatePublicationStatus,
  getAllPublication,
  getAllPublicationByUser,
  getPendingPublication,
  getUnapprovedPublication,
  deleteAdminPublication,
  deleteUserPublication,
} from "../controllers/publication.controller.js";

const router = Router();

router.post("/publication/create", authRequired, createPublication);
router.get("/publication/all", authRequired, getAllPublication);
router.get("/publication/user/:id", authRequired, getAllPublicationByUser);
router.delete("/publication/user/delete/:id", authRequired, deleteUserPublication);
router.put("/publication/admin/status/:id", authRequired, updatePublicationStatus);
router.get("/publication/admin/pending", authRequired, getPendingPublication);
router.get("/publication/admin/unapproved",authRequired, getUnapprovedPublication);
router.delete("/publication/admin/delete", authRequired, deleteAdminPublication);

export default router;