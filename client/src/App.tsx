import React from "react";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Home from "./routes/home";
import Error from "./routes/error";
import Login from "./routes/login";
import Layout from "./components/layout";
import Portfolio from "./routes/portfolio";
import Log from "./routes/log";
import Profile from "./routes/profile";

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Layout />,
      errorElement: <Error />,
      children: [
        { index: true, element: <Home /> },
        { path: "/portfolio", element: <Portfolio /> },
        { path: "/log", element: <Log /> },
        { path: "/profile", element: <Profile /> },
      ],
    },
    {
      path: "/login",
      element: <Login />,
    },
  ]);

  return <RouterProvider router={router} />;
}

export default App;
