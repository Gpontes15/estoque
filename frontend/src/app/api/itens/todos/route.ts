import { NextResponse } from 'next/server'

export async function GET() {
  try {
    const response = await fetch('http://minha-aplicacao:8080/itens', {
      next: { revalidate: 0 },
    })

    if (!response.ok) {
      throw new Error('Erro ao buscar itens')
    }

    const data = await response.json()
    return NextResponse.json(data)
  } catch (error) {
    console.error('Erro ao buscar itens:', error)
    return NextResponse.json({ error: 'Erro ao buscar itens' }, { status: 500 })
  }
}
