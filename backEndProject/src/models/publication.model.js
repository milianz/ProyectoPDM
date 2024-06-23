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
    floors: {
      type: Number,
      required: true,
    },
    rooms: {
      type: Number,
      required: true,
    },
    longitude: {
      type: Number,
      required: true,
    },
    latitude: {
      type: Number,
      required: true,
    },
    description: {
      type: String,
      required: true,
    },
    price: {
      type: Number,
      required: true,
    },
    date: {
      type: Date,
      default: Date.now,
    },
    image: {
      public_id: String,
      secure_url: String,
    },
    seller: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User',
      required: true
    },
    status: {
      type: String,
      enum: ["unapproved", "approved", "pending"],
      default: "pending"
    },
  },
  {
    timestamps: true,
  }
);

export default mongoose.model("Publication", publicationSchema);