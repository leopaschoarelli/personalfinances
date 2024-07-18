import axios from 'axios';
import qs from 'qs';

const BASE_URL = 'http://localhost:8080'; // URL do seu backend Spring

const AuthService = {
  login: async (username  : any, password : any) => {
    try {
      const postLogin = `${BASE_URL}/login`;

      const data = qs.stringify({
        username: username,
        password: password
      });

      const config = {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      };

      // Primeira chamada para /oauth2/authorize
      const authorizeUrl = `${BASE_URL}/oauth2/authorize?response_type=code&client_id=personalFinances&state=abc&redirect_uri=http://127.0.0.1:8080/authorized&scope=WRITE%20READ&code_challenge=8TzU5wMa2MzwXOW4SjwSh03i8nlh7nf5hNtXUh0bau8&code_challenge_method=S256`;

      const authorizeResponse = await axios.post(authorizeUrl);
      console.log(authorizeResponse);

      const loginResponse = await axios.post(postLogin, data, config);
      console.log(loginResponse);
      console.log('aqui');



      // Extrair o código da URL de retorno
      const authorizeCode = new URL(authorizeResponse.request.responseURL).searchParams.get('code');
      console.log(authorizeCode);

      // Segunda chamada para /oauth2/token
      const tokenUrl = `${BASE_URL}/oauth2/token`;
      const tokenResponse = await axios.post(tokenUrl, qs.stringify({
        grant_type: 'authorization_code',
        code: authorizeCode,
        redirect_uri: 'http://127.0.0.1:8080/authorized',
        code_verifier: 'personalfinances-auth', // código desafio
      }), config);

      // Retorna o token JWT
      console.log(tokenResponse.data.access_token);
      return tokenResponse.data.access_token;
    } catch (error : any) {
      console.error('Erro na autenticação:', error.response ? error.response.data : error.message);
      throw new Error('Falha na autenticação');
    }
  },
};

export default AuthService;