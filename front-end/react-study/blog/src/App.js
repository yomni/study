/* eslint-disable */
import React, {useState} from "react";
import './App.css';

function App() {
  let [글제목, 글제목변경] = useState(['남자코드 추천', '강남 우동맛집', '스프링독학']);
  let [따봉, 따봉변경] = useState(0);

  function 제목바꾸기() {
    let newArray = [...글제목];
    newArray.sort();
    글제목변경(newArray);
  }
  // TODO : router 학습
  return (
    <div className="App">
      <div className="black-nav">
        <div>개발 blog</div>
      </div>
      <button onClick={제목바꾸기}>버튼
      </button>
      <div className="list">
        <h3> {글제목[0]} <span onClick={() => {
          따봉변경(따봉 + 1)
        }}>👍🏻</span> {따봉} </h3>
        <p>2월 17일 발행</p>
        <hr/>
      </div>
      <div className="list">
        <h3> {글제목[1]} </h3>
        <p>2월 17일 발행</p>
        <hr/>
      </div>
      <div className="list">
        <h3> {글제목[2]} </h3>
        <p>2월 17일 발행</p>
        <hr/>
      </div>
    </div>
  );
}

export default App;
