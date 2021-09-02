import React, { useState } from 'react'
import { useHistory } from 'react-router-dom';

import api from '../../Service/api'

import './styles.css';

export default function Login () {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const history = useHistory();

    async function login(e){
        e.preventDefault();

        const data = {
            username,
            password
        }

        try {
            
            const response = await api.post('/auth/signing', data)

            localStorage.setItem("username", username)
            localStorage.setItem("acessToken", response.data.token)

            history.push("/")

        } catch (error) {
            alert('Error on login! Try Again')
        }
    }

    return (
        <div>
            <div className="container">
                <form onSubmit={login}>
                    <h1>Realize o Login para acessar o Sistema</h1>

                    <input type="text" placeholder="username" value={username} 
                        onChange={e => setUsername(e.target.value)}/>
                    <input type="password" placeholder="password" value={password}
                        onChange={e => setPassword(e.target.value)}/>

                    <div className="button">
                        <button type="submit">Login</button>
                    </div>
                    

                
                </form>
            </div>
        </div>
    );
}