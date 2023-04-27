import axios from "axios";

const axiosInstance = axios.create({
    withCredentials: false
  });


export async function fetchNotes() {
    const response = await axiosInstance.get(`http://localhost:8080/api/notes/`);
    const eventData = response.data;
    return eventData;
}