import mongoose from "mongoose";

const publicationSchema = new mongoose.Schema(
  {
    type: {
      type: String,
      required: true,
    },
    state: {
      type: String,
      required: true,
    },
    city: {
      type: String,
      required: true,
    },
    town: {
      type: String,
      required: true,
    },
    address: {
      type: String,
      required: true,
    },
    area: {
      type: Number,
      required: true,
    },
    floors:{
        type: Number,
        required: true,
    },
    rooms: {
      type: Number,
      required: true,
    },
    other: [
      {
        type: String,
      },
    ],
    description: {
      type: String,
      required: true,
    },
    price: {
      type: Number,
      required: true,
    },
    availability: {
      type: Map,
      of: [String],
      required: true,
    },
    images: [
      {
        type: String,
        required: true,
      },
    ],
    date: {
      type: Date,
      default: Date.now,
    },
    seller: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    status: {
      type: String,
      enum: ["unapproved", "approved", "pending"],
      default: "unapproved",
    },
  },
  {
    timestamps: true, 
  }
);

export default mongoose.model("Publication", publicationSchema);
