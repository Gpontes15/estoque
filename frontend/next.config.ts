/** @type {import('next').NextConfig} */
const nextConfig = {
  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: 'http://minha-aplicacao:8080/:path*',
      },
    ]
  },
}

module.exports = nextConfig
