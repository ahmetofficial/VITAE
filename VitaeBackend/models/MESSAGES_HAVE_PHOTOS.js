/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('MESSAGES_HAVE_PHOTOS', {
    conversation_reply_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'MESSAGES_REPLY',
        key: 'conversation_reply_id'
      }
    },
    photo_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'PHOTOS',
        key: 'photo_id'
      }
    }
  }, {
    tableName: 'MESSAGES_HAVE_PHOTOS'
  });
};
