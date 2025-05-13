// src/app/layout.tsx
'use client'

import Link from 'next/link'
import { Geist, Geist_Mono } from "next/font/google";
import './globals.css'

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body className={`${geistSans.variable} ${geistMono.variable} antialiased`}>
        {/* Navbar */}
        <nav className="bg-blue-500 text-white p-4 flex gap-4">
          <Link href="/dashboard">Dashboard</Link>
          <Link href="/login">Sair</Link>
          {/* No futuro: Adicione mais Links aqui */}
        </nav>

        {/* Conteúdo principal */}
        <main className="p-6">
          {children}
        </main>
      </body>
    </html>
  )
}
