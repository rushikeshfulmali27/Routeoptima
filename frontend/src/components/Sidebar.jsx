import React from 'react';
import { NavLink } from 'react-router-dom';

const Sidebar = () => {
    return (
        <div className="sidebar">
            <h2>RouteOptima</h2>
            <NavLink to="/" className={({ isActive }) => isActive ? 'active' : ''}>
                Dashboard
            </NavLink>
            <NavLink to="/ports" className={({ isActive }) => isActive ? 'active' : ''}>
                Port Manager
            </NavLink>
            <NavLink to="/routes" className={({ isActive }) => isActive ? 'active' : ''}>
                Route Planner
            </NavLink>
        </div>
    );
};

export default Sidebar;
