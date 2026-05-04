import axios from "axios";

const usageApi = axios.create({
  baseURL: "http://localhost:8080/api",
});

usageApi.interceptors.request.use((config) => {
  const apiKey = localStorage.getItem("apiKey");

  if (apiKey) {
    config.headers["x-api-key"] = apiKey;
  }

  return config;
});

export default usageApi;