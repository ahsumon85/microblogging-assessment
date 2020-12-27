export default function authHeader() {
  const accessToken = JSON.parse(localStorage.getItem('token'));

  if (accessToken && accessToken.access_token) {
    return { Authorization: 'Bearer ' + accessToken.access_token }; 
  } else {
    return {};
  }
}
