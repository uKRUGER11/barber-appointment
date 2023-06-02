import React, { useState } from "react";
import Input from "../../components/Input";
import Button from "../../components/Button";
import * as C from "./styles";
import { Link, useNavigate } from "react-router-dom";
import useAuth from "../../hooks/useAuth";

const Signup = () => {
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [phoneConf, setPhoneConf] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const { signup } = useAuth();

  const handleSignup = (e) => {
    e.preventDefault()
    const client={name, phone, password}
    console.log(client)
    fetch("http://localhost:8080/clients/add", {
      method: "POST",
      headers: {"Content-Type":"application/json"},
      body: JSON.stringify(client)
    }).then(() => {
      console.log("New client registered")
    })
  
    if (!name | !phone | !phoneConf | !password) {
      setError("Preencha todos os campos");
      return;
    } else if (phone !== phoneConf) {
      setError("Os números não são iguais!");
      return;
    }

    const res = signup(name, phone, password);

    if (res) {
      setError(res);
      return;
    }

    alert("Usuário cadatrado com sucesso!");
    navigate("/");
  };

  return (
    <C.Container>
      <C.Label>Cadastro</C.Label>
      <C.Content>
        <Input
          type="name"
          placeholder="Digite seu nome"
          value={name}
          onChange={(e) => [setName(e.target.value), setError("")]}
        />
        <Input
          type="phone"
          placeholder="Digite seu número do WhatsApp"
          value={phone}
          onChange={(e) => [setPhone(e.target.value), setError("")]}
        />
        <Input
          type="phone"
          placeholder="Confirme seu número do WhatsApp"
          value={phoneConf}
          onChange={(e) => [setPhoneConf(e.target.value), setError("")]}
        />
        <Input
          type="password"
          placeholder="Digite sua Senha"
          value={password}
          onChange={(e) => [setPassword(e.target.value), setError("")]}
        />
        <C.labelError>{error}</C.labelError>
        <Button Text="Inscrever-se" onClick={handleSignup} />
        <C.LabelSignin>
          Já tem uma conta?
          <C.Strong>
            <Link to="/">&nbsp;Entre</Link>
          </C.Strong>
        </C.LabelSignin>
      </C.Content>
    </C.Container>
  );
};

export default Signup;