import { Router } from "express";
import {authRequired} from "../middleware/validateToken.js"
import {createPublication, updatePublication, deletePublication, getAllPublication} from "../controllers/publication.controller.js"
const router = Router();

router.post('/publication',authRequired, createPublication)
router.get('/publication/all',authRequired, getAllPublication)
router.delete('/publication/:id',authRequired, updatePublication)
router.put('/publication/:id',authRequired, deletePublication)


export default router