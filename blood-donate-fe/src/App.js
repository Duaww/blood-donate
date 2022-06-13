import "./App.css";
import { LoginComponent } from "./login/LoginComponent";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { AuthError } from "./error/AuthError";
import { MyProfileComponent } from "./profile/MyProfileComponent";
import { CreatePostComponent } from "./post-find-donator/CreatePostComponent";
import { ListPostComponent } from "./post-find-donator/ListPostComponent";
import { PostDetailComponent } from "./post-find-donator/PostDetailComponent";
import { RegisterToDonateComponent } from "./register-to-donate/RegisterToDonateComponent";
import { DonatedBloodComponent } from "./donated-blood/DonatedBloodComponent";
import { AdminComponent } from "./admin/AdminComponent";
import { CreateHospitalComponent } from "./admin/CreateHospitalComponent";
import { ListHospitalComponent } from "./admin/ListHostpital";
import { ListDonatorComponent } from "./admin/ListDonator";

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
              <Route path="detail/:key" element={<PostDetailComponent />} />
              <Route
                path="register-post/:key"
                element={<RegisterToDonateComponent />}
              />
              <Route path="donated" element={<DonatedBloodComponent />} />
              <Route path="admin" element={<AdminComponent />} />
              <Route
                path="create-hospital"
                element={<CreateHospitalComponent />}
              />
              <Route path="hospital" element={<ListHospitalComponent />} />
              <Route path="donator" element={<ListDonatorComponent />} />
            </Routes>
          </header>
        </div>{" "}
      </Router>
    </div>
  );
}

export default App;
