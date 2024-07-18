import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './components/Login';

const App: React.FC = () => {
  return (
    <Router>
      <div>
        <Routes>
          <Route path='/' element={<Login/>} />
          {/* Adicione outras rotas aqui conforme necessário */}
        </Routes>
      </div>
    </Router>
  );
};

export default App;