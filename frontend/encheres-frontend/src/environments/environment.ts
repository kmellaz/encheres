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
        manuelle: '/encheres/offres/manuelle',
        automatique: '/encheres/offres/auto'
      }
    },
    clients: {
      base: '/encheres/clients'
    }
  }
};
