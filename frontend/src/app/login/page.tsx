'use client'

import { useState } from 'react'
import { useRouter } from 'next/navigation'

export default function LoginPage() {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const router = useRouter()

  const handleLogin = async () => {
    try {
      const response = await fetch('/api/usuarios/' + username)
      if (!response.ok) {
        throw new Error('Usuário não encontrado')
      }
      const user = await response.json()

      if (user.password === password) {
        localStorage.setItem('isLoggedIn', 'true')
        router.push('/dashboard')
      } else {
        alert('❌ Usuário ou senha inválidos.')
      }
    } catch (error) {
      console.error(error)
      alert('❌ Erro ao tentar se conectar com o servidor.')
    }
  }

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white p-6 rounded shadow-md w-80">
        <h1 className="text-2xl font-bold text-center text-black mb-6">Login</h1>
        <input
          type="text"
          placeholder="Usuário"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="w-full mb-4 p-2 border rounded text-black"
        />
        <input
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full mb-6 p-2 border rounded text-black"
        />
        <button
          onClick={handleLogin}
          className="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
          Entrar
        </button>
      </div>
    </div>
  )
}
