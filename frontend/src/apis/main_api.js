import axios from "axios";

const AXIOS = axios.create({
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Content-Type": "application/json; charset = utf-8"
  },
  timeout: 1000
});

export default {
  requestMainView() {
    return AXIOS.get(`${process.env.VUE_APP_BASEURL}/main`);
  }
}
