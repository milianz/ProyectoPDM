import express from 'express';
import morgan from 'morgan';
import cookieParser from 'cookie-parser';
import fileUpload from 'express-fileupload';
import cors from 'cors';

import authRoutes from './routes/auth.routes.js';
import publicationRoutes from './routes/publication.routes.js';

const app = express();

app.use(cors());
app.use(morgan('dev'));
app.use(express.json());
app.use(fileUpload({
    useTempFiles : true,
    tempFileDir : '/tmp'
}));
app.use(cookieParser());

app.use('/api', authRoutes);
app.use('/api', publicationRoutes);

export default app;