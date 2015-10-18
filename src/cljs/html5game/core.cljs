(ns html5game.core
  (:require
   goog.dom
   goog.style
   [devtools.core :as devtools]
   [html5game.future :as future]
   [html5game.game-loop]
   [html5game.state :refer [STATE] :as state]
   [om.core :as om :include-macros true]
   [om.dom :as dom :include-macros true]
   [sablono.core :as html :refer-macros [html]]))

(devtools/install!)

(enable-console-print!)

(defn view [{:keys [state] :as app} owner]
  (reify
    om/IRender
    (render [_]
      (html [:div {:id "page"}
             [:div {:id "top_bar"}]
             [:div {:id "game"}
              [:div {:id "board"}]]
             [:div {:id "footer_bar"}]
             [:div {:id "start_game"
                    :class (str "dialog " (if (= state :initial) "visible" "invisible"))
                    :on-click #(state/dispatch {:action :start-game})}
              [:div {:id "start_game_message"}
               [:h2 "Start a new Game"]]
              [:div {:class "but_start_game button"} "New Game"]]]))))

(om/root
 view
 STATE
 {:target (goog.dom.getElement "app")})
