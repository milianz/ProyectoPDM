import mongoose from "mongoose";
const uri ="mongodb+srv://milianz:root@cluster0.i5vog4i.mongodb.net/PDMPROJECT"
export const connectDB = async()=>{
    try {

        await mongoose.connect(uri);
        console.log(">>>>> DB is connected");
        
    } catch (error) {
        console.log(error);
    }
}