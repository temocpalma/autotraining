import axios from 'axios';

const apiUrl = "http://localhost:3001/homes";

const getHomeList = () => {
  axios.get(apiUrl)
      .then(response => {
        console.log(response.data);
        return response.data;
      })
      .catch(error => {
        console.log(error);
        return [];
      })
}

export { getHomeList };