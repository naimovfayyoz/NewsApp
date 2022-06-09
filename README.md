# NewsApp

### Create News app

Get the news from API and show them in the main screen with recycler view

get API Key and add it to header of every request with the help of `OkHttpClient` -> `.addHeader("Authorization", API_KEY)`: https://newsapi.org/docs/authentication


With the help of this request get top headlines : 
`GET: https://newsapi.org/docs/endpoints/top-headlines?country=<country_symb>`

Response sample:
      
       {
          "status": "ok",
          "totalResults": 38,
          "articles": [
              {
                "source": {
                    "id": "cnn",
                    "name": "CNN"
                },
                "author": "Tara Subramaniam, Camilo Rocha, Marcia Reverdosa",
                "title": "Suspect held as Brazil steps up search for missing British journalist and researcher in remote Amazon - CNN",
                "description": "Brazilian authorities said Wednesday they have detained a suspect in the case of a missing British journalist and indigenous affairs expert -- but are yet to establish whether the man was linked to their disappearance in a remote region of the Amazon.",
                "url": "https://www.cnn.com/2022/06/09/americas/dom-phillips-bruno-pereira-missing-suspect-intl-latam/index.html",
                "urlToImage": "https://media.cnn.com/api/v1/images/stellar/prod/220608232335-03-dom-phillips-journalist.jpg?c=16x9&q=w_800,c_fill",
                "publishedAt": "2022-06-09T09:28:00Z",
                "content": "Brazilian authorities said Wednesday they have detained a suspect in the case of a missing British journalist and indigenous affairs expert but are yet to establish whether the man was linked to thei… [+3473 chars]"
              }
          ]
        }
