import api from "./api";

export const getSubscription = () => {
  return api.get("/subscription/current");
};