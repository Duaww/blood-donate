import "./Admin.css";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export const AdminComponent = (props) => {
  const navigate = useNavigate();

  function createNewHospital() {
    navigate("/create-hospital");
  }

  return (
    <div>
      <input
        type="button"
        value="Create new Hospital"
        onClick={() => createNewHospital()}
      />
    </div>
  );
};
