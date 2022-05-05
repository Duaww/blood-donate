
import './App.css';
import { LoginComponent } from './login/LoginComponent';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { AuthError } from './error/AuthError';
import { MyProfileComponent } from './profile/MyProfileComponent';
import { CreatePostComponent } from './post-find-donator/CreatePostComponent';
import { ListPostComponent } from './post-find-donator/ListPostComponent';

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
              <Route path="my-profile" element={<MyProfileComponent />} />
              <Route path="create-post" element={<CreatePostComponent />} />
              <Route path="list-post" element={<ListPostComponent />} />
            </Routes>
          </header>
        </div>{" "}
      </Router>
    </div>
  );
}

export default App;
