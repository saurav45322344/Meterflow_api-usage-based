// import api from "./api";

// export const getBilling = (apiKey) =>
//   api.get(`/billing?apiKey=${apiKey}`);


import api from "./api";

export const getBilling = (apiKey) => {
  return api.get(`/billing?apiKey=${apiKey}`);
};