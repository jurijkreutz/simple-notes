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
    axios
      .post("http://localhost:8080/api/notes", newNote)
      .then((response) => {
        console.log(response.status);
      })
}