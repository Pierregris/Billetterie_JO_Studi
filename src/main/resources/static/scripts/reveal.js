ScrollReveal().reveal('.reveal',{
        origin: 'left',
        distance: '0px',
        duration: 1000,
        easing: 'ease-in-out',
        opacity:0});


if (window.innerWidth>=768){
ScrollReveal().reveal('.reveal-left',{
  origin: 'left',
  distance: '100px',
  duration: 1000,
  easing: 'ease-in-out'});

  ScrollReveal().reveal('.reveal-right',{ 
    origin: 'right',
    distance: '100px',
    duration: 1000,
    easing: 'ease-in-out'});

  ScrollReveal().reveal('.reveal-bottom',{ 
    origin: 'bottom',
    distance: '100px',
    duration: 1000,
    easing: 'ease-in-out'});

  ScrollReveal().reveal('.reveal-top',{ 
    origin: 'top',
    distance: '0px',
    duration: 500,
    easing: 'ease-in-out'});}

    if (window.innerWidth<=768){
      ScrollReveal().reveal('.reveal-left',{
        origin: 'left',
        distance: '0px',
        duration: 1000,
        easing: 'ease-in-out',
        opacity:0});

      ScrollReveal().reveal('.reveal-right',{ 
        origin: 'right',
        distance: '0px',
        duration: 1000,
        easing: 'ease-in-out',
        opacity:0});

      ScrollReveal().reveal('.reveal-bottom',{ 
        origin: 'bottom',
        distance: '0px',
        duration: 1000,
        easing: 'ease-in-out',
        opacity:0});

      ScrollReveal().reveal('.reveal-top',{ 
        origin: 'top',
        distance: '0px',
        duration: 500,
        easing: 'ease-in-out',
        opacity:0});}