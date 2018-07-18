import axios from "axios";

export class BaseService {
  constructor(baseUrl) {
    this.baseUrl = process.env.API_URL + baseUrl;
  }

  get(token, query) {
    if (token === null) {
      token = "";
    }
    return axios
      .get(`${this.baseUrl}${token}`, query)
      .then(result => result.data);
  }

  post(object) {
    return axios.post(this.baseUrl, object).then(result => result.data);
  }

  getById(id, token) {
    if (token === null) {
      token = "";
    }
    return axios
      .get(`${this.baseUrl}/${id}${token}`)
      .then(result => result.data);
  }

  rawGet(url, query) {
    return axios.get(url, query).then(result => result.data);
  }

  save(object) {
    const saveAction = object.id
      ? axios.put(`${this.baseUrl}/${object.id}`, object)
      : axios.post(this.baseUrl, object);

    return saveAction.then(result => result.data);
  }

  delete(id) {
    return axios.delete(`${this.baseUrl}/${id}`);
  }

  put(url, object) {
    return axios.put(url, object).then(result => result.data);
  }
}
