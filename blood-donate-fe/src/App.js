
import './App.css';
import { LoginComponent } from './login/LoginComponent';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { AuthError } from './error/AuthError';
import { MyProfile } from './profile/MyProfile';

function App() {
  return (
    <div>
      <Router>
        {/* <div className="">
          <Header />
        </div> */}
        <div className="">
          <header className="">
            <Routes>
              <Route path="/" element={<LoginComponent />} />
              <Route path="auth-error" element={<AuthError />} />
              <Route path="my-profile" element={<MyProfile />} />
            </Routes>
          </header>
        </div>{" "}
      </Router>
    </div>
  );
}

export default App;
