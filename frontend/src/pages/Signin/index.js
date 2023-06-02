
import React, { useState } from "react";
import Input from "../../components/Input";
import Button from "../../components/Button";
import * as C from "./styles";
import { Link, useNavigate } from "react-router-dom";
import useAuth from "../../hooks/useAuth";

const Signin = () => {
  const { signin } = useAuth();
  const navigate = useNavigate();

  const [phone, setPhone] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = (e) => {
    e.preventDefault()
    const client={phone, password}
    console.log(client)
    fetch("http://localhost:8080/clients/login", {
      method: "POST",
      headers: {"Content-Type":"application/json"},
      body: JSON.stringify(client)
    }).then(() => {
      console.log("")
    })

    const res = signin(phone, password);

    if (!res) {
      setError(res);
      return;
    } else {
      alert("Horário marcado com sucesso!");
      navigate("/calendar");
    }

   
  };

  return (
    <C.Container>
      <C.Label>Login</C.Label>
      <C.Content>
        <Input
          type="phone"
          placeholder="Digite seu número do WhatsApp"
          value={phone}
          onChange={(e) => [setPhone(e.target.value), setError("")]}
        />
        <Input
          type="password"
          placeholder="Digite sua Senha"
          value={password}
          onChange={(e) => [setPassword(e.target.value), setError("")]}
        />
        <C.labelError>{error}</C.labelError>
        <Button Text="Entrar" onClick={handleLogin} />
        <C.LabelSignup>
          Não tem uma conta?
          <C.Strong>
            <Link to="/signup">&nbsp;Registre-se</Link>
          </C.Strong>
        </C.LabelSignup>
      </C.Content>
    </C.Container>
  );
};

export default Signin;