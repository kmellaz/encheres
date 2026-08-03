export const environment = {
  production: true,
  api: {
    baseUrl: 'https://api.encheres.prod/api',
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
