import axios from "axios";

const baseURL = import.meta.env.VITE_BASE_URL ?? 'http://localhost:8080';

export const request = axios.create({ baseURL });