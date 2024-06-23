import mongoose from 'mongoose';

const imageSchema = new mongoose.Schema({
  public_id: String,
  secure_url: String,
});

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
    seller: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User',
      required: true,
    },
    status: {
      type: String,
      enum: ['unapproved', 'approved', 'pending'],
      default: 'pending',
    },
    images: [imageSchema], 
  },
  {
    timestamps: true,
  }
);

export default mongoose.model("Publication", publicationSchema);