import api from "./api";

export const getUsage = (apiKey) =>
  api.get(`/usage?apiKey=${apiKey}`);