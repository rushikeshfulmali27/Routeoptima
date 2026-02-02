import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import Dashboard from './pages/Dashboard';
import Ports from './pages/Ports';
import RoutePlanner from './pages/RoutePlanner';

function App() {
  return (
    <Router>
      <div className="layout">
        <Sidebar />
        <div className="content">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/ports" element={<Ports />} />
            <Route path="/routes" element={<RoutePlanner />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
