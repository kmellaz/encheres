export const environment = {
  production: false,
  api: {
    baseUrl: 'http://localhost:8080/api',
    auth: {
      login: '/auth/login'
    },
    encheres: {
      base: '/encheres',
      offres: {
        manuelle: '/offres/manuelle',
        automatique: '/offres/auto'
      }
    },
    clients: {
      base: '/encheres/clients'
    }
  }
};
