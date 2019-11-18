import axios from "axios";

export const addProjectTask = (projectTask, history) => async dispatch => {
  await axios.post("http://127.0.0.1:8080/api/board", projectTask);
  history.push("/");
};
