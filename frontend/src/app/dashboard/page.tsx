'use client'

import { useEffect, useState } from "react"

interface Item {
  id: number
  nome: string
  codigo: string
  descricao: string
  preco: number
}

export default function DashboardPage() {
  const [itens, setItens] = useState<Item[]>([])
  const [nome, setNome] = useState('')
  const [codigo, setCodigo] = useState('')
  const [descricao, setDescricao] = useState('')
  const [preco, setPreco] = useState<number>(0)
  const [editandoId, setEditandoId] = useState<number | null>(null)

  useEffect(() => {
    buscarItens()
  }, [])

  async function buscarItens() {
    try {
      const response = await fetch('/api/itens')
      if (!response.ok) throw new Error('Erro ao buscar itens')
      const data = await response.json()
      setItens(Array.isArray(data) ? data : [])
    } catch (error) {
      console.error('Erro ao buscar itens:', error)
      setItens([])
    }
  }

  async function salvarItem() {
    if (!nome || !codigo || preco <= 0) {
      alert('Preencha todos os campos obrigatórios.')
      return
    }

    const item = { nome, codigo, descricao, preco }

    const url = editandoId === null
      ? '/api/itens'
      : `/api/itens/${editandoId}`

    const method = editandoId === null ? 'POST' : 'PUT'

    await fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(item),
    })

    limparFormulario()
    buscarItens()
  }

  async function excluirItem(id: number) {
    await fetch(`/api/itens/${id}`, { method: 'DELETE' })
    buscarItens()
  }

  function editarItem(item: Item) {
    setNome(item.nome)
    setCodigo(item.codigo)
    setDescricao(item.descricao)
    setPreco(item.preco)
    setEditandoId(item.id)
  }

  function limparFormulario() {
    setNome('')
    setCodigo('')
    setDescricao('')
    setPreco(0)
    setEditandoId(null)
  }

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">📦 Dashboard de Itens</h1>

      <div className="mb-6 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <input
          type="text"
          placeholder="Nome do item"
          value={nome}
          onChange={(e) => setNome(e.target.value)}
          className="border p-2 rounded"
        />
        <input
          type="text"
          placeholder="Código do item"
          value={codigo}
          onChange={(e) => setCodigo(e.target.value)}
          className="border p-2 rounded"
        />
        <input
          type="text"
          placeholder="Descrição"
          value={descricao}
          onChange={(e) => setDescricao(e.target.value)}
          className="border p-2 rounded"
        />
        <input
          type="number"
          step="0.01"
          placeholder="Preço (R$)"
          value={preco}
          onChange={(e) => setPreco(Number(e.target.value))}
          className="border p-2 rounded"
        />
      </div>

      <div className="mb-6">
        <button onClick={salvarItem} className="bg-green-500 text-white px-6 py-2 rounded hover:bg-green-600">
          {editandoId ? 'Atualizar' : 'Cadastrar'}
        </button>
      </div>

      <table className="w-full bg-white rounded shadow">
        <thead>
          <tr className="bg-gray-200">
            <th className="text-black p-2">Nome</th>
            <th className="text-black p-2">Código</th>
            <th className="text-black p-2">Descrição</th>
            <th className="text-black p-2">Preço (R$)</th>
            <th className="text-black p-2">Ações</th>
          </tr>
        </thead>
        <tbody>
          {itens.length === 0 ? (
            <tr>
              <td colSpan={5} className="text-center p-4 text-gray-500">Nenhum item cadastrado.</td>
            </tr>
          ) : (
            itens.map((item) => (
              <tr key={item.id} className="text-center border-t">
                <td className="p-2 text-black">{item.nome}</td>
                <td className="p-2 text-black">{item.codigo}</td>
                <td className="p-2 text-black">{item.descricao}</td>
                <td className="p-2 text-black">R$ {item.preco.toFixed(2)}</td>
                <td className="p-2 flex gap-2 justify-center">
                  <button onClick={() => editarItem(item)} className="bg-yellow-400 px-2 py-1 rounded hover:bg-yellow-500">
                    Editar
                  </button>
                  <button onClick={() => excluirItem(item.id)} className="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600">
                    Excluir
                  </button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  )
}
