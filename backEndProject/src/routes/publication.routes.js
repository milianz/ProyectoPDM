import { Router } from "express";
import {authRequired} from "../middleware/validateToken.js"
import {createPublication, updatePublicationStatus, getAllPublication,getPendingPublication,getUnapprovedPublication} from "../controllers/publication.controller.js"
const router = Router();

router.post('/publication/create',authRequired, createPublication)
router.get('/publication/all',authRequired, getAllPublication)
router.put('/publication/status/:id',authRequired, updatePublicationStatus)
router.get('/publication/pending',authRequired,getPendingPublication)
router.get('/publication/unapproved',authRequired,getUnapprovedPublication)


export default router