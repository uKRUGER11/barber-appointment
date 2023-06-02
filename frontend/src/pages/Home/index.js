import dayjs from "dayjs";
import React, { useState } from "react"; 
import { useNavigate } from "react-router-dom";
import { GrFormNext, GrFormPrevious } from "react-icons/gr";
import { generateDate, months } from "../../util/calendar";
import cn from "../../util/cn";
import useAuth from "../../hooks/useAuth";
import * as C from "./styles";
import { Button2 } from "../../components/Button2/styles";


const Home = () => {
  
  console.log(generateDate());

  const { signout } = useAuth();
  const navigate = useNavigate();

  const days = ["DOM", "SEG", "TER", "QUA", "QUI", "SEX", "SÁB"];
  const hours = ["8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00"];
  const currentDate = dayjs();
  const [today, setToday] = useState(currentDate);
  const [selectDate, setSelectDate] = useState(currentDate);

  return (
    <C.Container>
      <div className="flex w-1/2 mx-auto divide-x-2 gap-10 h-screen items-center">
      <div className="w-96 h-96 ">
        <div className="flex justify-between">
            <h1 className="font-semibold">
              {months[today.month()]}, {today.year()}
            </h1>
            <div className="flex items-center gap-5">
                <GrFormPrevious className="w-5 h-5 cursor-pointer" 
                onClick={() => {
                  setToday(today.month(today.month() - 1));
                }} 
                />
                <h1 className="cursor-pointer" 
                onClick={() => {
                  setToday(currentDate);
                }}
                > Hoje
                </h1>
                <GrFormNext className="w-5 h-5 cursor-pointer"
                onClick={() => {
                  setToday(today.month(today.month() + 1));
                }} 
                />
            </div>
        </div>
        <div className="w-full grid grid-cols-7 text-gray-500">
          {days.map((day, index) => {
            return (
              <h1 key={index} className="h-14 grid place-content-center text-sm">
                {day}
              </h1>
            );
          })}
        </div>
        <div className="w-full grid grid-cols-7">
          {generateDate(today.month(), today.year()).map(({ date, currentMonth, today }, index) => {
            return (
              <div key={index} className="h-14 border-t grid place-content-center text-sm">
                <h1 className={cn(
                today ? "bg-black-900 text-white" : " ", selectDate.toDate().toDateString() === date.toDate().toDateString() ? "bg-black text-white" : "",
                currentMonth?"":"text-gray-400", 
                currentMonth?"bg-blue-600 text-white" : "",
                "h-10 w-10 grid place-content-center rounded-full hover:bg-black hover:text-white transition-all cursor-pointer",
                )}
                  onClick={() => {
                    setSelectDate(date);
                  }}
                >
                  {date.date()}
                </h1>
              </div>
            );
          })}
        </div>
      </div>
      <div className="h-96 w-96 px-5">
        <h1 className=" font-semibold gap-5"> 
          Horários de {selectDate.toDate().toLocaleDateString('pt-BR')}
        </h1>
        <div className="h-80 w-80 flex flex-col items-center mt-5 cursor-pointer">
        {hours.map((hours, index) => {
            return (
              <h1 key={index} className= "grid place-content-center text-sm h-80 w-80 grid place-content-center rounded-full hover:bg-blue-600 hover:text-white transition-all cursor-pointer">
              <Button2 onClick={() => [signout(), navigate("/")]}>
                {hours}
              </Button2>
              </h1>
            );
          })}
        </div>
      </div>
    </div>
    </C.Container>
  );
};

export default Home;