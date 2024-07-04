import mongoose from "mongoose";

const userSchema = new mongoose.Schema({
    name:{ 
        type: String,
        required: true,
        trim: true
    },
    lastName:{
        type: String,
        required: true,
        trim: true

    },
    email: { 
        type: String,
        required: true,
        trim: true,
        unique: true
    },
    role:{
        type : String,
        required: true, 
        enum: ["admin", "user"],
          default: "user",
    },
    password:{
        type: String,
        required: true,
    },
    isVerified:{
        type: Boolean,
        default: false
    },
},{
    timestamps: true
})

export default mongoose.model('User', userSchema)