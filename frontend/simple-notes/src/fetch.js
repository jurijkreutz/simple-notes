import axios from "axios";

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
    withCredentials: false
  });


export async function fetchNotes() {
    const response = await axiosInstance.get(`/api/notes`);
    const eventData = response.data;
    return eventData;
}

export async function addNote(title, content) {
    const newNote = {
        title: title,
        content: content
      }
    return await axios
      .post("http://localhost:8080/api/notes", newNote)
      .then((response) => {
        return response.status;
      })
}

export async function removeNote(noteId) {
  const response = await axiosInstance.delete(`/api/notes/${noteId}`);
  return response.status;
}