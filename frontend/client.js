
const videoDiv = document.querySelector('#video-player');
const videoScreen = document.querySelector('#video-screen');
const queryParams = Object.fromEntries(new URLSearchParams(window.location.search));



fetch('http://172.20.10.6:8081/video/all')
    .then(result => result.json())
    .then(result => {

        const myVids = document.querySelector('#your-videos');
        if(result.length > 0){
            for(let vid of result){
                const li = document.createElement('LI');
                const link = document.createElement('A');
                link.innerText = vid;
                link.href = window.location.origin + window.location.pathname + '?video=' + vid;
                li.appendChild(link);
                myVids.appendChild(li);
            }
        }else{
            myVids.innerHTML = 'No videos found';
        }
  });

  // based on the above code, i want to get the video from the server and play it on the video screen the video is streaming from the server
  if(queryParams.video){
    videoScreen.src = `http://172.20.10.6:8081/video/${queryParams.video}`;
    videoDiv.style.display = 'block';
    document.querySelector('#now-playing')
        .innerText = 'Now playing ' + queryParams.video;

}

if (window.matchMedia("(max-width: 600px)").matches) {
    /* the viewport is less than 600 pixels wide */
    document.querySelector("#video-list").style.width = "100%";
    document.querySelector("#video-player").style.width = "100%";
  } else {
    /* the viewport is at least 600 pixels wide */
    document.querySelector("#video-list").style.width = "30%";
    document.querySelector("#video-player").style.width = "70%";
  }
  