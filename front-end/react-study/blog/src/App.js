/* eslint-disable */
import React, {useState} from "react";
import './App.css';

function App() {
  let [글제목, 글제목변경] = useState(['남자코트 추천', '강남 우동맛집', '스프링 독학']);
  let [따봉, 따봉변경] = useState(0);

  return (
    <div className="App">
      <div className="black-nav">
        <div>개발 blog</div>
      </div>
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

      <Modal state={글제목}></Modal>

    </div>
  );
}

function Modal(props) {
  return (
    <div className="modal">
      <h4>{props.state}</h4>
      <p>날짜</p>
      <p>상세내용</p>
    </div>
  )
}

export default App;
