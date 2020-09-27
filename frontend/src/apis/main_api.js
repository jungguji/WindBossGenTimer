import { AXIOS } from "./http_common";

export default {
  requestMainView() {
    return AXIOS.get(`${process.env.VUE_APP_BASEURL}/region`);
  }
};
